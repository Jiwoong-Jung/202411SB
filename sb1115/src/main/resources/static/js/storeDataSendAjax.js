function dataSend() {

    let data=$("#input").val();

    let messageDTO={
        result:data
    };

    $.ajax({
        url: "/dataSend",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#resultDiv").replaceWith(fragment);
    });
}