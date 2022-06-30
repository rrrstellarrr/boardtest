$(function(){
    $(".default_option").click(function(){
        if($(".dropdown ul").hasClass("active")) {
            $(".dropdown ul").removeClass("active");
        } else {
            $(".dropdown ul").addClass("active");
        }
    });

    $(".dropdown ul li").click(function(){
        var text = $(this).text();
        $(".default_option").text(text);
        $(".dropdown ul").removeClass("active");
    });

    $("#searchBtn").click(function(){
        const data = {
            category : $(".default_option").text(),
            search : $("#search").val()
        };

        if(!data.search || data.search.trim() == "") {
            alert("검색어를 입력해주세요.");
            $("#searchText").focus();
            return false;
        }
    });
})