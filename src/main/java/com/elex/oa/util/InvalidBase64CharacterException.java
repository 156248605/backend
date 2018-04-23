package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:34
*/
class InvalidBase64CharacterException extends IllegalArgumentException{
    InvalidBase64CharacterException(String message) {
        super(message);
    }
}
