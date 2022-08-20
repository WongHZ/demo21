var stompClient = null;
function setConnected(connected){
    $("#connet").prop("disabled",connected);
    $("#disconnect").prop("disable",!connected);
    if(connected){
        $("#conversation").show();
        $("#chat").show();
    }
    else {
        $("#conversation").hide();
        $("#chat").hide();
    }
    $("#greetings").html("");
}
/*
*使用SocketJS建立链接，然后创建一个STOMP实例发起连接请求，
* 链接成功的回调方法中，首先调用setConnected(true)进行页面
* 设置，然后调用STOMP的subscribe订阅服务发送回来的消息，并将服务端发送来的消息用showGreeting展示出来
*/
function connect(){
    if(!$("#name").val()){
        return;
    }
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({},function(frame){
        setConnected(true);
        stompClient.subscribe('/topic/greetings',function(greeting){
            showGreeting(JSON.parse(greeting.body));
        });
    });
}
/*
* 调用STOMP的disconnect断开一个WebSocket连接
*/
function  disconnect(){
    if(stompClient !== null){
        stompClient.disconnect();
    }
    setConnected(false);
}
function  sendName(){
    stompClient.send("/app/hello",{},
        JSON.stringify({'name': $("#name").val(),'content': $("#content").val()})
    );
}
function showGreeting(message){
    $("#greetings")
        .append("<div>" + message.name + ":" +message.content + "</div>");
}
$(function (){
    $("#connet").click(function (){connect();});
    $("#disconnect").click(function (){disconnect();});
    $("#send").click(function (){sendName();});
})