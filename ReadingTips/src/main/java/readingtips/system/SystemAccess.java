package readingtips.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import readingtips.BlogPost;
import readingtips.Book;
import readingtips.Podcast;
import readingtips.Tip;
import readingtips.Video;
import readingtips.database.PodcastDao;
import readingtips.database.VideoDao;
import readingtips.system.call.LinuxUbuntuCall;
import readingtips.system.call.SystemCall;
import readingtips.system.call.WindowsMsysCall;

public class SystemAccess {

    private final String mediaPath = "downloaded_media_files/files";

    private final static Map<String, SystemCall> osToSystemCallMap;
    static {
        osToSystemCallMap = new HashMap<String, SystemCall>();
        osToSystemCallMap.put("Windows 10", new WindowsMsysCall()); // os.name: Windows 10
        osToSystemCallMap.put("Linux", new LinuxUbuntuCall()); // os.name: Linux
    }

    private static SystemCall systemSpecific() {
        return osToSystemCallMap.get(System.getProperty("os.name"));
    }

    private SystemCall systemCall;

    private VideoDao videoDao = new VideoDao();
    private PodcastDao podcastDao = new PodcastDao();

    public SystemAccess() {
        this.systemCall = systemSpecific();
    }

    public void updateLength(Video video) {
        try {
            String commandPath = "command/get_media_length.sh";
            List<String> getMediaLengthCommandLine = Arrays.asList(commandPath, mediaPath + "/" + video.getUrl());
            String secondsString = systemCall.systemCall(getMediaLengthCommandLine);
            long seconds = Long.parseLong(secondsString);
            video.setLength(seconds);
        } catch (Exception x) {
            // ok to fail here.
            // throw new RuntimeException(x);   // development
        }
    }

    public void updateLength(Podcast podcast) {
        if(podcast.getPodcastName().startsWith("http")) {
            // no length
            return;
        }

        try {
            String commandPath = "command/get_media_length.sh";
            List<String> getMediaLengthCommandLine = Arrays.asList(commandPath,
                    mediaPath + "/" + podcast.getPodcastName());
            String secondsString = systemCall.systemCall(getMediaLengthCommandLine);
            long seconds = Long.parseLong(secondsString);
            podcast.setLength(seconds);
        } catch (Exception x) {
            // ok to fail here.
            // throw new RuntimeException(x);   // development            
        }
    }

    public void open(Tip tip) {

        try {
            if (tip instanceof Video) {
                Video video = (Video) tip;
                long newPosition = playVideo(mediaPath + "/" + video.getUrl(), video.getPosition());
                System.out.println("got new video position: " + newPosition + " seconds");
                video.setPosition(newPosition);
                videoDao.update(video);
            }

            if (tip instanceof Podcast) {
                Podcast podcast = (Podcast) tip;

                // check if we have web url or local file
                String url = podcast.getPodcastName();
                if (url.startsWith("http")) {
                    openWebUrl(url);
                } else if (url.endsWith(".mp4")) {
                    long newPosition = playVideo(mediaPath + "/" + url, podcast.getPosition());
                    System.out.println("got new video position: " + newPosition + " seconds");
                    podcast.setPosition(newPosition);
                    podcastDao.update(podcast);
                } else {
                    String[] audioSuffixes = new String[] { "webm", "m4a" };
                    if (Arrays.stream(audioSuffixes).anyMatch(entry -> url.endsWith(entry))) {
                        long newPosition = playAudio(mediaPath + "/" + url, podcast.getPosition());
                        System.out.println("got new audio position: " + newPosition + " seconds");
                        podcast.setPosition(newPosition);
                        podcastDao.update(podcast);
                    } else {
                        System.out.println("can NOT open file: " + url);
                    }
                }
            }

            if (tip instanceof BlogPost) {
                BlogPost blogPost = (BlogPost) tip;
                String url = blogPost.getUrl();
                openWebUrl(url);
            }

            if (tip instanceof Book) {
                Book book = (Book) tip;
                String url = "https://www.google.com/search?q='" + book.getTitle() + " " + book.getIsbn() + "'";
                openWebUrl(url);
            }

        } catch (Exception x) {
            // throw in developemnt
            // throw new RuntimeException(x);
            System.out.println("??what happened??");
        }
    }

    private long playVideo(String filePath, long positionSeconds) {
        String commandPath = "command/play_video_with_mpv.sh";
        List<String> playVideoCommandLine = Arrays.asList(commandPath, filePath, positionSeconds + "");
        System.out.println("playVideoCommandLine: " + playVideoCommandLine);
        String secondsString = systemCall.systemCall(playVideoCommandLine);
        long seconds = Long.parseLong(secondsString);
        return seconds;
    }

    private long playAudio(String filePath, long positionSeconds) {
        String commandPath = "command/play_audio_with_mpv.sh";
        List<String> playAudioCommandLine = Arrays.asList(commandPath, filePath, positionSeconds + "");
        System.out.println("playVideoAudioLine: " + playAudioCommandLine);
        String secondsString = systemCall.systemCall(playAudioCommandLine);
        long seconds = Long.parseLong(secondsString);
        return seconds;
    }

    private void openWebUrl(String url) {
        String commandPath = "command/open_web_url.sh";
        List<String> openWebUrlCommandLine = Arrays.asList(commandPath, url);
        systemCall.systemCall(openWebUrlCommandLine);
    }

}
