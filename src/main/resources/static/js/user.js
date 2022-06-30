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
        $("#username_result").text("");
    });

    const b_email = $("#email").val();
    $("input[name=email]").focusout(function(){
        const email = $("#email").val();
        console.log(email);

        if(!isEmptyObj(email) && (b_email != email)) {
            $.ajax({
                type: "POST",
                url: "/check/email",
                data: {email: email}
            }).done(function(result) {
            console.log(result);
                if(result == "success") {
                    $("#email_result").text("사용가능한 이메일입니다.");
                    $("#email_result").css("color", "green");
                    $("#update_account").prop("disabled", false);
                } else {
                    $("#email_result").text("이미 사용중인 이메일입니다.");
                    $("#email_result").css("color", "red");
                    $("#update_account").prop("disabled", true);
                }
            }).fail(function (error) {
                alert("에러");
            });
        }
        $("#email_result").text("");
    });

    const b_nickname = $("#nickname").val();
    $("input[name=nickname]").focusout(function(){
        const nickname = $("#nickname").val();
        console.log(nickname);

        if(!isEmptyObj(nickname) && (b_nickname != nickname)) {
            $.ajax({
                type: "POST",
                url: "/check/nickname",
                data: {nickname: nickname}
            }).done(function(result) {
            console.log(result);
                if(result == "success") {
                    $("#nickname_result").text("사용가능한 닉네임입니다.");
                    $("#nickname_result").css("color", "green");
                    $("#update_account").prop("disabled", false);
                } else {
                    $("#nickname_result").text("이미 사용중인 닉네임입니다.");
                    $("#nickname_result").css("color", "red");
                    $("#update_account").prop("disabled", true);
                }
            }).fail(function (error) {
                alert("에러");
            });
        }
        $("#nickname_result").text("");
    });

    $("#update_account").on("click", function(){
        const password = $("#password").val();
        const email = $("#email").val();
        const nickname = $("#nickname").val();

        if(isEmptyObj(password)) {
            alert("비밀번호를 입력해주세요.");
            $("#password").focus();
            return false;
        }
        if(isEmptyObj(email)) {
            alert("비밀번호를 입력해주세요.");
            $("#email").focus();
            return false;
        }
        if(isEmptyObj(nickname)) {
             alert("비밀번호를 입력해주세요.");
             $("#nickname").focus();
             return false;
         }
    });

    $("#delete_account").on("click", function(){
        const id = $("#userIdx").val();
        const chkPassword = $("#chkPassword").val();

        if(confirm("회원 탈퇴하시겠습니까?")) {
            if(isEmptyObj(chkPassword)) {
                alert("비밀번호를 확인해주세요.");
                $("#chkPassword").focus();
                return false;
            }
        }
    });

    $("#profileImage").change(function() {
        readURL(this);
    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            console.log(reader);
            reader.onload = function(e) {
                $("#imagePreview").css("background-image", "url("+e.target.result +")");
                $("#imagePreview").fadeIn(650);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
});