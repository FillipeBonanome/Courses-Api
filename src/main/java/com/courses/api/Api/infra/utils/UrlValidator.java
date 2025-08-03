package com.courses.api.Api.infra.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UrlValidator {

    private static final String YOUTUBE_REGEX =
            "^(https?://)?(www\\.)?(m\\.)?(youtube\\.com|youtu\\.be|youtube-nocookie\\.com)" +
                    "/(watch\\?v=|embed/|v/|e/|shorts/|ytscreeningroom\\?v=|user/.+/|.+\\?v=)?([\\w-]{11})(.*)?$";

    private static final Pattern YOUTUBE_PATTERN = Pattern.compile(YOUTUBE_REGEX);

    public boolean isValidYouTubeUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = YOUTUBE_PATTERN.matcher(url);
        return matcher.matches();
    }

}
