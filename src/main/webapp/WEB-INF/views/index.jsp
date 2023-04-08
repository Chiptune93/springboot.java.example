<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang=ko>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    </head>
    <body>
        <div>
            <form name="form" method="post" action="/upload.do" enctype="multipart/form-data">
                file upload
                <br/>
                <input type="file" name="singleFile" />
                <button type="submit">submit</button>
            </form>
            <form name="form2" method="post" action="/upload2.do" enctype="multipart/form-data">
                file upload2
                <br/>
                <input type="file" name="singleFile2" />
                <button type="submit">submit</button>
            </form>
            <form name="form3" method="post" action="/upload3.do" enctype="multipart/form-data">
                file upload3
                <br/>
                <input type="file" name="singleFile3" />
                <button type="submit">submit</button>
            </form>
            <form name="form4" method="post" action="/upload4.do" enctype="multipart/form-data">
                file upload4
                <br/>
                <input type="file" name="files" multiple />
                <button type="submit">submit</button>
            </form>
            <a id="download" href="/download.do?seq=4">다운로드</a>
        </div>
    </body>
</html>