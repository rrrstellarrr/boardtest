$(function(){

    $("#saveBtn").on("click", function(){
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        if(!data.title || data.title.trim() == "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }
        if(!data.content || data.content.trim() == "") {
            alert("내용을 입력해주세요.");
            $("#content").focus();
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/api/kkeujeok/write",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function(result) {
            alert(result.message);
            window.location.href="/kkeujeok/list";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    });

    $("#updateBtn").on("click", function(){
        const data = {
            id: $("#boardIdx").val(),
            title: $("#title").val(),
            content: $("#content").val()
         };
        if(data.title == "") {
            alert("제목을 입력해주세요.");
            return false;
        }
        if(data.content == "") {
            alert("내용을 입력해주세요.");
            return false;
        }
        $.ajax({
            type: "PUT",
            url: "/api/kkeujeok/update/" + data.id,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            async:false
        }).done(function(result) {
            alert(result.message);
            window.location.href="/kkeujeok/read/" + data.id;
        }).fail(function (error) {
            alert("실패하였습니다." + JSON.stringify(error));
        });
    });

    $("#deleteBtn").on("click", function(){
        const id = $("#boardIdx").val();

        if(confirm("게시글을 삭제하시겠습니까?")) {
            $.ajax({
                type: "DELETE",
                url: "/api/kkeujeok/delete/" + id,
                dataType: "JSON",
                contentType: "application/json; charset=utf-8"
            }).done(function(result) {
                alert(result.message);
                window.location.href="/kkeujeok/list";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    });

    $("#replyDelBtn").on("click", function(){
        const commentId = $("#commentIdx").val();

        if(confirm("댓글을 삭제하시겠습니까?")) {
            $.ajax({
                type: "DELETE",
                url: "/api/comment/delete/" + commentId,
                dataType: "JSON",
                contentType: "application/json; charset=utf-8"
            }).done(function(result) {
                alert(result.message);
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    })

    $("#replyBtn").on("click", function(){
        const data = {
            content : $("#replyContent").val(),
            id : $("#boardIdx").val()
        };
        $.ajax({
            type: "POST",
            url: "/api/comment/" + data.id,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function(result) {
            alert(result.message);
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    });

    $(".default-option").click(function(){
        if($(".dropdown ul").hasClass("active")) {
            $(".dropdown ul").removeClass("active");
        } else {
            $(".dropdown ul").addClass("active");
        }
    });

    $(".dropdown ul li").click(function(){
        const text = $(this).text();
        $(".default-option").text(text);
        $(".dropdown ul").removeClass("active");
    });

});