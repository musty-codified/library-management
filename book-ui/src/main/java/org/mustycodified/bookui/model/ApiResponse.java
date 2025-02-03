package org.mustycodified.bookui.model;

import java.sql.Timestamp;

import java.time.format.DateTimeFormatter;


public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final String timestamp = new Timestamp(System.currentTimeMillis())
            .toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+0000"));
    private final T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }

    public static class Wrapper<T> {

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

        public int getTotalItemsCurrentPage() {
            return totalItemsCurrentPage;
        }

        public void setTotalItemsCurrentPage(int totalItemsCurrentPage) {
            this.totalItemsCurrentPage = totalItemsCurrentPage;
        }

        public boolean isLastPage() {
            return isLastPage;
        }

        public void setLastPage(boolean lastPage) {
            isLastPage = lastPage;
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

        public boolean isFirstPage() {
            return isFirstPage;
        }

        public void setFirstPage(boolean firstPage) {
            isFirstPage = firstPage;
        }

        private T content;
        private int totalPages;
        private long totalItems;
        private int totalItemsCurrentPage;
        private int currentPage;
        private boolean isLastPage;
        private boolean isFirstPage;
        private boolean isEmpty;
    }

}
