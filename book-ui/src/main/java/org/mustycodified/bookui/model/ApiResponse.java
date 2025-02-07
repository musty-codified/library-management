package org.mustycodified.bookui.model;

public class ApiResponse<T> {
    private final T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }


    public static class Wrapper<T> {


        public Wrapper(T content, int totalPages, long totalItems, int totalItemsCurrentPage) {
            this.content = content;
            this.totalPages = totalPages;
            this.totalItems = totalItems;
            this.currentPage = totalItemsCurrentPage;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public long getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(long totalItems) {
            this.totalItems = totalItems;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }




        private T content;
        private int totalPages;
        private long totalItems;
        private int currentPage;

    }

}
