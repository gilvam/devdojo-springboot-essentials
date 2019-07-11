package br.com.devdojo.error;

import java.util.List;

public class ValidationErrorDetails extends ErrorDetail {
    private List<String> fields;
    private List<String> fieldMessages;
    private List<String> objectNames;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFieldMessages() {
        return fieldMessages;
    }

    public void setFieldMessages(List<String> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public List<String> getObjectNames() {
        return objectNames;
    }

    public void setObjectNames(List<String> objectNames) {
        this.objectNames = objectNames;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private Long timestamp;
        private String developerMessage;
        private List<String> fields;
        private List<String> fieldMessages;
        private List<String> objectNames;

        private Builder() {
        }

        public static ValidationErrorDetails.Builder newBuilder() {
            return new ValidationErrorDetails.Builder();
        }

        public ValidationErrorDetails.Builder title(String title) {
            this.title = title;
            return this;
        }

        public ValidationErrorDetails.Builder status(int status) {
            this.status = status;
            return this;
        }

        public ValidationErrorDetails.Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ValidationErrorDetails.Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidationErrorDetails.Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ValidationErrorDetails.Builder field(List<String> fields) {
            this.fields = fields;
            return this;
        }

        public ValidationErrorDetails.Builder fieldMessage(List<String> fieldMessages) {
            this.fieldMessages = fieldMessages;
            return this;
        }

        public ValidationErrorDetails.Builder objectNames(List<String> objectNames) {
            this.objectNames = objectNames;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails resourceNotFoundDetails = new ValidationErrorDetails();
            resourceNotFoundDetails.setDeveloperMessage(this.developerMessage);
            resourceNotFoundDetails.setStatus(this.status);
            resourceNotFoundDetails.setTimestamp(this.timestamp);
            resourceNotFoundDetails.setTitle(this.title);
            resourceNotFoundDetails.setDetail(this.detail);
            resourceNotFoundDetails.fields = fields;
            resourceNotFoundDetails.fieldMessages = fieldMessages;
            resourceNotFoundDetails.objectNames = objectNames;
            return resourceNotFoundDetails;
        }
    }
}
