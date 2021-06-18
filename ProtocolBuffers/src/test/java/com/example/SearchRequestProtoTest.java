package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchRequestProtoTest {

    private SearchRequestProto.SearchRequest getSearchRequest() {
        return SearchRequestProto.SearchRequest.newBuilder()
                .setKeyword("keyword")
                .setPage(1)
                .setSize(10)
//                .setSort(SearchRequestProto.SearchRequest.Sort.DESC)
                .build();
    }

    @Test
    public void simple() {
        SearchRequestProto.SearchRequest searchRequest = getSearchRequest();

        assertThat(searchRequest.getKeyword()).isEquals("keyword");
        assertThat(searchRequest.getPage()).isEquals(1);
        assertThat(searchRequest.getSize()).isEquals(10);
        assertThat(searchRequest.getSort()).isEquals(SearchRequestProto.SearchRequest.Sort.ASC);
    }

}
