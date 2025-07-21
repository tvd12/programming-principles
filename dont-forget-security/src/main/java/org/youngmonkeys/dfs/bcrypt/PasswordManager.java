/*
 * Copyright 2022 youngmonkeys.org
 * 
 * Licensed under the ezyplatform, Version 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://youngmonkeys.org/licenses/ezyplatform-1.0.0.txt
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.youngmonkeys.dfs.bcrypt;

import com.tvd12.ezyfox.security.BCrypt;
import com.tvd12.ezyfox.util.EzyLoggable;

public class PasswordManager extends EzyLoggable {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isMatchingPassword(
        String password,
        String hashedPassword
    ) {
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            logger.info(
                "password is not matching cause by: {} ({})",
                e.getClass(),
                e.getMessage()
            );
            return false;
        }
    }
}
