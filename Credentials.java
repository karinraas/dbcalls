public class Credentials {
        private final String user = "";
        private final String pass = "";

        public String getUser() {
            return name;
        }
        public void setUser (String user){
            this.user = user;
        }
        public String getPass() {
            return pass;
        }
        public void setPass() {
            this.pass = pass;
        }

    protected String setCred(String user, String pass){
        user = "admin";
        pass = "admin";
        return user,pass;
    }
}
