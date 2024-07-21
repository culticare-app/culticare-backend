package com.culticare.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostsTagMap is a Querydsl query type for PostsTagMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostsTagMap extends EntityPathBase<PostsTagMap> {

    private static final long serialVersionUID = -2130948768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostsTagMap postsTagMap = new QPostsTagMap("postsTagMap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosts post;

    public final SimplePath<org.aspectj.apache.bcel.generic.Tag> tag = createSimple("tag", org.aspectj.apache.bcel.generic.Tag.class);

    public QPostsTagMap(String variable) {
        this(PostsTagMap.class, forVariable(variable), INITS);
    }

    public QPostsTagMap(Path<? extends PostsTagMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostsTagMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostsTagMap(PathMetadata metadata, PathInits inits) {
        this(PostsTagMap.class, metadata, inits);
    }

    public QPostsTagMap(Class<? extends PostsTagMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPosts(forProperty("post"), inits.get("post")) : null;
    }

}

