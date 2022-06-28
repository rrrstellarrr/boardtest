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
            url: "/write",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function() {
            alert('등록되었습니다.');
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
            url: "/update/" + data.id,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "TEXT",
            async:false
        }).done(function() {
            alert("수정되었습니다.");
            window.location.href="/kkeujeok/read/" + data.id;
        }).fail(function (error) {
            alert("실패하였습니다." + JSON.stringify(error));
        });
    });

    $("#deleteBtn").on("click", function(){
        const id = $("#boardIdx").val();

        if(confirm("회원탈퇴를 하시겠습니까?")) {
            $.ajax({
                type: "DELETE",
                url: "/delete/" + id,
                dataType: "JSON",
                contentType: "application/json; charset=utf-8"
            }).done(function() {
                alert("삭제되었습니다.");
                window.location.href="/kkeujeok";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    });

    $("#sort-new").on("click", function(){
        if(!$("#sort-new .sortby-item").hasClass("checked")) {
            $("#sort-new .sortby-item").addClass(" checked");
            if($("#sort-views .sortby-item").hasClass("checked")) {
                $("#sort-views .sortby-item").attr("class", "sortby-item")
            }
        }
    });
    $("#sort-views").on("click", function(){
        if(!$("#sort-views .sortby-item").hasClass("checked")) {
            $("#sort-views .sortby-item").addClass(" checked");
            if($("#sort-new .sortby-item").hasClass("checked")) {
                $("#sort-new .sortby-item").attr("class", "sortby-item")
            }
        }
    });

    $("#replyBtn").on("click", function(){
        const data = {
            content : $("#replyContent").val(),
            id : $("#boardIdx").val()
        };
        $.ajax({
            type: "POST",
            url: "/comment/" + data.id,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function() {
            alert("댓글작성이 완료되었습니다.");
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