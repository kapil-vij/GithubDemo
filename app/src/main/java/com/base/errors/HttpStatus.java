/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.errors;

/**
 * Created by kapilvij on 25/05/20.
 */

public interface HttpStatus {
    int BAD_REQUEST = 400;
    int AUTHORIZATION = 401;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int SERVER_ERROR = 500;
    int NO_CONTENT = 204;
}
