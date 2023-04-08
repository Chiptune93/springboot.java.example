package dev.chiptune.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.chiptune.springboot.data.addUserRes;
import dev.chiptune.springboot.data.data;
import dev.chiptune.springboot.data.deleteUserRes;
import dev.chiptune.springboot.data.getUserRes;
import dev.chiptune.springboot.data.updateUserRes;
import dev.chiptune.springboot.response.ApiErr;
import dev.chiptune.springboot.response.ApiNoAuth;
import dev.chiptune.springboot.response.ApiRes;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "user", description = "get user list")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Tag(name = "user")
    @ApiOperation(value = "유저 리스트", notes = "파라미터로 넘어온 수 만큼 유저를 리턴한다.", authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = ApiRes.class))),
            @ApiResponse(responseCode = "201", description = "500과 동일"),
            @ApiResponse(responseCode = "401", description = "권한 없음(키누락)", content = @Content(schema = @Schema(implementation = ApiNoAuth.class))),
            @ApiResponse(responseCode = "403", description = "500과 동일"),
            @ApiResponse(responseCode = "404", description = "500과 동일"),
            @ApiResponse(responseCode = "500", description = "요청 실패", content = @Content(schema = @Schema(implementation = ApiErr.class)))
    })
    @GetMapping(value = "/{version}/getUser", produces = "application/json")
    public ResponseEntity<ApiRes<getUserRes>> getUser(@RequestParam int num,
            @Parameter(name = "version", description = "API 버전을 입력하세요. ", example = "V1", required = true) @PathVariable String version) {
        getUserRes res = new getUserRes();
        List<data> result = new ArrayList<data>();
        for (int i = 0; i < num; i++) {
            data d = new data();
            d.setUserNo(i);
            d.setUsername("user" + i);
            result.add(d);
        }
        res.setDataList(result);
        return new ResponseEntity<ApiRes<getUserRes>>(new ApiRes<getUserRes>(res), HttpStatus.OK);
    }

    @Tag(name = "user")
    @ApiOperation(value = "유저 추가", notes = "새로운 사용자를 추가한다.", authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = ApiRes.class))),
            @ApiResponse(responseCode = "201", description = "500과 동일"),
            @ApiResponse(responseCode = "401", description = "권한 없음(키누락)", content = @Content(schema = @Schema(implementation = ApiNoAuth.class))),
            @ApiResponse(responseCode = "403", description = "500과 동일"),
            @ApiResponse(responseCode = "404", description = "500과 동일"),
            @ApiResponse(responseCode = "500", description = "요청 실패", content = @Content(schema = @Schema(implementation = ApiErr.class)))
    })
    @PostMapping(value = "/{version}/addUser", produces = "application/json")
    public ResponseEntity<ApiRes<addUserRes>> addUser(@RequestBody data param,
            @Parameter(name = "version", description = "API 버전을 입력하세요. ", example = "V1", required = true) @PathVariable String version) {
        addUserRes res = new addUserRes();
        res.setUserNo(param.getUserNo());
        return new ResponseEntity<ApiRes<addUserRes>>(new ApiRes<addUserRes>(res), HttpStatus.OK);
    }

    @Tag(name = "user")
    @ApiOperation(value = "유저 업데이트", notes = "사용자를 업데이트 한다.", authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = ApiRes.class))),
            @ApiResponse(responseCode = "201", description = "500과 동일"),
            @ApiResponse(responseCode = "401", description = "권한 없음(키누락)", content = @Content(schema = @Schema(implementation = ApiNoAuth.class))),
            @ApiResponse(responseCode = "403", description = "500과 동일"),
            @ApiResponse(responseCode = "404", description = "500과 동일"),
            @ApiResponse(responseCode = "500", description = "요청 실패", content = @Content(schema = @Schema(implementation = ApiErr.class)))
    })
    @PutMapping(value = "/{version}/updateUser", produces = "application/json")
    public ResponseEntity<ApiRes<updateUserRes>> updateUser(@RequestBody data param,
            @Parameter(name = "version", description = "API 버전을 입력하세요. ", example = "V1", required = true) @PathVariable String version) {
        updateUserRes res = new updateUserRes();
        res.setUserNo(param.getUserNo());
        return new ResponseEntity<ApiRes<updateUserRes>>(new ApiRes<updateUserRes>(res), HttpStatus.OK);
    }

    @Tag(name = "user")
    @ApiOperation(value = "유저 삭제", notes = "파라미터로 넘어온 데이터의 유저를 삭제한다.", authorizations = {
            @Authorization(value = "apiKey") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = ApiRes.class))),
            @ApiResponse(responseCode = "201", description = "500과 동일"),
            @ApiResponse(responseCode = "401", description = "권한 없음(키누락)", content = @Content(schema = @Schema(implementation = ApiNoAuth.class))),
            @ApiResponse(responseCode = "403", description = "500과 동일"),
            @ApiResponse(responseCode = "404", description = "500과 동일"),
            @ApiResponse(responseCode = "500", description = "요청 실패", content = @Content(schema = @Schema(implementation = ApiErr.class)))
    })
    @DeleteMapping(value = "/{version}/deleteUser", produces = "application/json")
    public ResponseEntity<ApiRes<deleteUserRes>> deleteUser(@RequestBody data param,
            @Parameter(name = "version", description = "API 버전을 입력하세요. ", example = "V1", required = true) @PathVariable String version) {
        deleteUserRes res = new deleteUserRes();
        res.setUserNo(param.getUserNo());
        return new ResponseEntity<ApiRes<deleteUserRes>>(new ApiRes<deleteUserRes>(res), HttpStatus.OK);
    }
}
