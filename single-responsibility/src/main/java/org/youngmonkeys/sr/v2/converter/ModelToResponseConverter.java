package org.youngmonkeys.sr.v2.converter;

import org.youngmonkeys.sr.v1.response.JwtAuthenticationResponse;
import org.youngmonkeys.sr.v2.model.Token;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ModelToResponseConverter {

    public JwtAuthenticationResponse toJwtResponse(Token token) {
        return new JwtAuthenticationResponse("", "", 0L);
    }
}
