$(function(){
    function isEmptyObj(value) {
        if( value == "" || value == " " || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length) ){
            return true;
        } else {
            return false;
        }
    }

    $("input[name=username]").focusout(function(){
        const username = $("#username").val();
        console.log(username);

        if(!isEmptyObj(username)) {
            $.ajax({
                type: "POST",
                url: "/check/username",
                data: {username: username}
            }).done(function(result) {
            console.log(result);
                if(result == "success") {
                    $("#username_result").text("사용가능한 아이디입니다.");
                    $("#username_result").css("color", "green");
                } else {
                    $("#username_result").text("이미 사용중인 아이디입니다.");
                    $("#username_result").css("color", "red");
                }
            }).fail(function (error) {
                alert("에러");
            });
        }
    });

    $("input[name=email]").focusout(function(){
        const email = $("#email").val();
        console.log(email);

        if(!isEmptyObj(email)) {
            $.ajax({
                type: "POST",
                url: "/check/email",
                data: {email: email}
            }).done(function(result) {
            console.log(result);
                if(result == "success") {
                    $("#email_result").text("사용가능한 이메일입니다.");
                    $("#email_result").css("color", "green");
                } else {
                    $("#email_result").text("이미 사용중인 이메일입니다.");
                    $("#email_result").css("color", "red");
                }
            }).fail(function (error) {
                alert("에러");
            });
        }
    });

    $("input[name=nickname]").focusout(function(){
        const nickname = $("#nickname").val();
        console.log(nickname);

        if(!isEmptyObj(nickname)) {
            $.ajax({
                type: "POST",
                url: "/check/nickname",
                data: {nickname: nickname}
            }).done(function(result) {
            console.log(result);
                if(result == "success") {
                    $("#nickname_result").text("사용가능한 닉네임입니다.");
                    $("#nickname_result").css("color", "green");
                } else {
                    $("#nickname_result").text("이미 사용중인 닉네임입니다.");
                    $("#nickname_result").css("color", "red");
                }
            }).fail(function (error) {
                alert("에러");
            });
        }
    });

    $("#update_account").on("click", function(){
        const data = {
            password:$("#password").val(),
            email:$("#email").val(),
            nickname:$("#nickname").val(),
        };
        $.ajax({
            type:"PUT",
            enctype: 'multipart/form-data',
            url:"/myinfo/update",
            dataType: "JSON",
            data:JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            processData : false,
            async:false
        }).done(function(fragment){
            console.log(fragment);
            password:$("#mypage-form").replaceWith(fragment);
            alert("회원수정이 완료되었습니다.");
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });

    $("#imageUpload").change(function() {
        readURL(this);
    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            console.log(reader);
            reader.onload = function(e) {
                $('#imagePreview').css('background-image', 'url('+e.target.result +')');
                $('#imagePreview').fadeIn(650);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
});