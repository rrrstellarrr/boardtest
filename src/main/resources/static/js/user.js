$(function(){
    function isEmptyObj(value) {
        if( value == "" || value == " " || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length) ){
            return true;
        } else {
            return false;
        }
    }

    $("#update_account").on("click", function(){
        const data = {
            userIdx: $("#userIdx").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            nickname: $("#nickname").val()
        }
        const image = $("#profileImage")[0].files[0];
        const form = new FormData();
        form.append("updateData", new Blob([JSON.stringify(data)], {type: "application/json"}));
        form.append("profileImage", image);

        if(isEmptyObj(data.password)) {
            alert("비밀번호를 입력해주세요.");
            $("#password").focus();
            return false;
        }
        if(isEmptyObj(data.email)) {
            alert("이메일을 입력해주세요.");
            $("#email").focus();
            return false;
        }
        if(isEmptyObj(data.nickname)) {
             alert("닉네임을 입력해주세요.");
             $("#nickname").focus();
             return false;
         }

        if(confirm("회원정보를 수정하시겠습니까?")) {
            $.ajax({
                type: "PUT",
                url: "/api/mypage/myinfo/update",
                contentType: false,
                processData: false,
                async: false,
                data: form
            }).done(function(data) {
                $.each(data,function(key, value) {
                    alert(value);
                });
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }

    });

    $("#delete_account").on("click", function(){
        const chkPassword = $("#chkPassword").val();
        if(confirm("회원 탈퇴하시겠습니까?")) {
            if(isEmptyObj(chkPassword)) {
                alert("비밀번호를 확인해주세요.");
                $("#chkPassword").focus();
                return false;
            }
            $.ajax({
                type: "DELETE",
                url: "/api/mypage/myinfo/delete",
                data: chkPassword,
                contentType: "application/json; charset=utf-8",
                async: false
            }).done(function(result) {
                alert(result.message);
                window.location.href="/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
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