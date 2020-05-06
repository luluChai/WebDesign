function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "id":questionId,
            "content":content,
        }),
        success: function (response) {
            console.log(response);
        },
        dataType: "json"
    });
}