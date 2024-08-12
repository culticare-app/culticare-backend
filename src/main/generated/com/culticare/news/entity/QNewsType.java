package com.culticare.news.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNewsType is a Querydsl query type for NewsType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsType extends EntityPathBase<NewsType> {

    private static final long serialVersionUID = -621380904L;

    public static final QNewsType newsType = new QNewsType("newsType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<News, QNews> newsList = this.<News, QNews>createList("newsList", News.class, QNews.class, PathInits.DIRECT2);

    public QNewsType(String variable) {
        super(NewsType.class, forVariable(variable));
    }

    public QNewsType(Path<? extends NewsType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewsType(PathMetadata metadata) {
        super(NewsType.class, metadata);
    }

}

