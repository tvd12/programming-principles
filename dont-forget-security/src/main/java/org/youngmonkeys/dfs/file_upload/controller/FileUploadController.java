package org.youngmonkeys.dfs.file_upload.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import lombok.AllArgsConstructor;
import org.youngmonkeys.dfs.file_upload.data.FileMetadata;
import org.youngmonkeys.dfs.file_upload.validator.MediaValidator;
import org.youngmonkeys.dfs.xss.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

@AllArgsConstructor
@Controller("/api/v1")
public class FileUploadController {

    private final MediaValidator mediaValidator;

    @DoPost("/file-upload")
    public ResponseEntity mediaAddPost(
        HttpServletRequest request
    ) throws Exception {
        Part filePart = request.getPart("file");
        if (filePart == null) {
            Collection<Part> parts = request.getParts();
            if (!parts.isEmpty()) {
                filePart = parts.iterator().next();
            }
        }
        if (filePart == null) {
            throw new BadRequestException("filePath is null");
        }
        FileMetadata metadata = mediaValidator.validateFilePart(filePart);
        Path file = Paths.get("test." + metadata.getExtension());
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }
        return ResponseEntity.noContent();
    }
}
