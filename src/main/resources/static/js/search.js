//var = main = {
//    init : function () {
//        var _this = this;
//        $('#btn-matjip-search').on('click', function () {
//            _this.find();
//         });
//    },
//    find : function () {
//        var keyword = $('#keyword').val();
//        $.ajax({
//            type: 'GET',
//            url: '/api/matjip/search?query=${query}',
//            dataType: 'json',
//            contentType:'application/json; charset=utf-8',
//            success: function (response) {
//                $('#search-result-box').empty();
//                for (let i = 0; i < response.length; i++) {
//                    let itemDto = response[i];
//                    $('#search-result-box').append(itemDto);
//                }
//            }
//        });
//    }
//};
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
//            title : $("#title").val(),
//            nickname : $("#nickname").val(),
            category : $(".default_option").text(),
            search : $("#search").val()
        };

        if(!data.search || data.search.trim() == "") {
            alert("검색어를 입력해주세요.");
            $("#searchText").focus();
            return false;
        }
//        $.ajax({
//            type : "GET",
//            url : "/kkeujeok/search?category=" + encodeURI(data.category) + "&search=" + encodeURI(data.search),
//            dataType: "JSON",
//            contentType: "application/json; charset=utf-8",
//            async:false
//        });
    });
})