const initUser = function (){

        let userId = document.cookie.match(new RegExp("(^| )"+'userId'+"=([^;]*)(;|$)"));
        if(userId != null){
            return unescape(userId[2]);
        }
        this.$router.push('/login');
}
export default initUser;
