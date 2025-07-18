package org.youngmonkeys.dfs.file_upload.service;

import com.tvd12.ezyfox.collect.Sets;
import com.tvd12.ezyhttp.core.constant.ContentType;
import com.tvd12.ezyhttp.server.core.annotation.Service;

import java.util.Set;

@Service
public class SettingService {

    public long getMaxUploadFileSize() {
        return 50 * 1024 * 1024;
    }

    public Set<String> getAcceptedMediaMimeTypes() {
        return Sets.newHashSet(
            ContentType.IMAGE_JPEG.getMimeType(),
            ContentType.IMAGE_JPG.getMimeType(),
            ContentType.IMAGE_PNG.getMimeType()
        );
    }
}
