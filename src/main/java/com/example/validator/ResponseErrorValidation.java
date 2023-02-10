package com.example.validator;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidation {

// получаю ошибку при логине
    public ResponseEntity<Object> mapValidationService(BindingResult result){

        if (result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();



// если у меня мапа не пустая то будем лупить все ошибки чтобы выкинуть их
            if (!CollectionUtils.isEmpty(result.getAllErrors())){
                for (ObjectError error : result.getAllErrors()){
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                }
            }
            //у меня могут быть разные ошибки как и с аттрибьютом так и с обьектом
            for (FieldError error : result.getFieldErrors()){
                    errorMap.put(error.getField(), error.getDefaultMessage());
            }
            // если есть ошибку то вернуть их
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        // а если все хорошо то вернуть ничего
        return null;
    }

}
