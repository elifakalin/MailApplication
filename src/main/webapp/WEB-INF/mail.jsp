<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <head>
        <title>Mail Gönder</title>
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>

        <style>
            .row {
                padding: 5px;
            }

        </style>
    </head>
</head>
<body>
<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            Mail Gönderme Servisi
        </div>
        <div class="panel-body">
            <div class="row">
                <label for="slcMailType" class="col-sm-2">Mail Tipi</label>
                <div class="col-sm-4">
                    <select id="slcMailType" class="form-control" onchange="onChange(this)">
                        <option value="WELCOME">Hoşgeldiniz</option>
                        <option value="FORGOTPASSWORD">Şifre Yenileme</option>
                        <option value="NEWSLETTER">Günlük Haberler</option>
                    </select>
                </div>

            </div>

            <div class="row">
                <label for="txtName" class="col-sm-2">İsim</label>
                <div class="col-sm-4">
                    <input type="text" id="txtName" class="form-control">
                </div>
            </div>
            <div class="row">
                <label for="txtLastName" class="col-sm-2">Soyisim</label>
                <div class="col-sm-4">
                    <input type="text" id="txtLastName" class="form-control">
                </div>
            </div>
            <div class="row">
                <label for="passwordUrl" class="col-sm-2">Şifre Yenileme Linki</label>
                <div class="col-sm-4">
                    <input type="text" id="passwordUrl" class="form-control">

                </div>
            </div>
            <div class="row">
                <label for="txtLastName" class="col-sm-2">Haber Tarihi</label>
                <div class="col-sm-4">
                    <input type="text" id="newsDate" class="form-control" placeholder="dd/MM/yyyy">

                </div>
            </div>
            <div class="row">
                <label for="newsLetter" class="col-sm-2">Haberler</label>
                <div class="col-sm-4">
                    <textarea type="text" id="newsLetter" class="form-control"></textarea>
                </div>
            </div>

            <button class="btn btn-default" onclick="x()"> Mail Gönder</button>
        </div>
    </div>
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>


<script type="text/javascript">

    window.onload = function () {
        $('#newsDate').parent().parent().hide();
        $('#newsLetter').parent().parent().hide();
        $('#passwordUrl').parent().parent().hide();
    };

    function x() {
        var type = $('#slcMailType').val();
        var data = {
            mailType: type
        };
        if (type === "WELCOME") {
            data.name = $('#txtName').val();
            data.lastName = $('#txtLastName').val()
        } else if (type === "FORGOTPASSWORD") {
            data.resetPasswordUrl = $('#passwordUrl').val()
        } else {
            data.name = $('#txtName').val();
            data.lastName = $('#txtLastName').val()
            data.letterDate = $('#newsDate').val();
            data.newsletter = $('#newsLetter').val()
        }

        $.ajax({
            url: '/send-mail',
            method: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data),
            success: function (data) {
                toastr.success(data);
                clear();
            },
            error: function (e) {
                console.log("error" + e)

            }
        });
    }

    function clear() {
        $('#txtName').val("");
        $('#txtLastName').val("");
        $('#txtLastName').val("");
        $('#newsDate').val("");
        $('#newsLetter').val("");
    }

    function onChange(type) {
        if (type.value === "WELCOME") {
            $('#txtName').parent().parent().show();
            $('#txtLastName').parent().parent().show();
            $('#newsDate').parent().parent().hide();
            $('#newsLetter').parent().parent().hide();
            $('#passwordUrl').parent().parent().hide();
        } else if (type.value === "FORGOTPASSWORD") {
            $('#txtName').parent().parent().hide();
            $('#txtLastName').parent().parent().hide();
            $('#newsDate').parent().parent().hide();
            $('#newsLetter').parent().parent().hide();
            $('#passwordUrl').parent().parent().show();
        } else {
            $('#txtName').parent().parent().show();
            $('#txtLastName').parent().parent().show();
            $('#newsDate').parent().parent().show();
            $('#newsLetter').parent().parent().show();
            $('#passwordUrl').parent().parent().hide();
        }
        console.log(type.value)
    }


</script>
</body>
</html>

