package dev.chiptune.springboot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.chiptune.springboot.response.ApiResFormat;
import dev.chiptune.springboot.service.ApiDataService;

/**
 * @apiNote Api Controller Sample
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@RestController
public class UserController {

        @Autowired
        ApiDataService a;

        /**
         * get users data
         * 
         * @param request
         * @param param
         * @return
         */
        @GetMapping(value = "/users/{version}/get", produces = "application/json; charset=utf8")
        public ResponseEntity<ApiResFormat> getUsers(HttpServletRequest request,
                        @RequestParam HashMap<String, Object> param,
                        @PathVariable String version) {
                switch (version) {
                        case "1.0":
                                return new ResponseEntity<ApiResFormat>(new ApiResFormat(a.getV1(param)),
                                                HttpStatus.OK);
                        case "2.0":
                                return new ResponseEntity<ApiResFormat>(new ApiResFormat(a.getV2(param)),
                                                HttpStatus.OK);
                        default:
                                return new ResponseEntity<ApiResFormat>(new ApiResFormat(null),
                                                HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        /**
         * post users data
         * 
         * @param request
         * @param param
         * @return
         */
        @PostMapping(value = "/users", produces = "application/json; charset=utf8")
        public ResponseEntity<ApiResFormat> postUsers(HttpServletRequest request,
                        @RequestBody HashMap<String, Object> param) {
                System.out.println("post get users");
                return new ResponseEntity<ApiResFormat>(new ApiResFormat(a.getData2(param)),
                                HttpStatus.OK);
        }

        /* unused */
        /*
         * @PutMapping(value = "/users/save", produces =
         * "application/json; charset=utf8")
         * public void updateUsers() {
         * System.out.println("put save users");
         * }
         * 
         * @DeleteMapping(value = "/users/delete", produces =
         * "application/json; charset=utf8")
         * public void deleteUsers() {
         * System.out.println("delete del users");
         * }
         */
}
