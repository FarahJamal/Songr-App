package me.farah.songrapp.model.dto;

    public class BlogDTO {
        private String user;
        private String content;

        public BlogDTO(String user, String content) {
            this.user = user;
            this.content=content;

        }
        public BlogDTO() {
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

