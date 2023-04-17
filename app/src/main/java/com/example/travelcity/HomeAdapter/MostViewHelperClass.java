package com.example.travelcity.HomeAdapter;

public class MostViewHelperClass {

        int image;
        String title,description;

        public MostViewHelperClass(int image, String title, String description) {
            this.image = image;
            this.title = title;
            this.description = description;
        }

        public int getImageView() {
            return image;
        }

        public String getTitleView() {
            return title;
        }

        public String getDescription() {
        return description;
    }


}


