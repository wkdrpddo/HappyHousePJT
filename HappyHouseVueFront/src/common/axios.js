import axios from 'axios';

export default axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        "Content-type": "application/json"
    },
    // default : false, false인 경우 = sessionId를 매번 새로 발급받는다. (session 유지를 안함)
    // 만약 해당 부분을 지우면, 어떠한 요청 시, session이 계속해서 새롭게 요청되어 server-session을 사용할 수 없게된다.
    withCredentials: true
});
