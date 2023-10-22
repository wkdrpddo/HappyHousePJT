const CLIENT_ID = "8289f4d4297b900f81ef062f93e14083";
// const REDIRECT_URI = "http://localhost:5500/kakaologin";

const REDIRECT_URI = "http://localhost:8080/kakaologin";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
