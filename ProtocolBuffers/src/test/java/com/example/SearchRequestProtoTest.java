package com.example;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchRequestProtoTest {

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

        assertThat(searchRequest.getKeyword(), is("keyword"));
        assertThat(searchRequest.getPage(), is(1));
        assertThat(searchRequest.getSize(), is(10));
        assertThat(searchRequest.getSort(), is(SearchRequestProto.SearchRequest.Sort.ASC));
    }

}
