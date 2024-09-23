package BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

public class MaxSizeExcetpion extends MaxUploadSizeExceededException {
    public MaxSizeExcetpion(Long fileSize) {
        super(fileSize);
    }
}
