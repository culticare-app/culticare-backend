package com.culticare.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostsHashtagMap is a Querydsl query type for PostsHashtagMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostsHashtagMap extends EntityPathBase<PostsHashtagMap> {

    private static final long serialVersionUID = 75783406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostsHashtagMap postsHashtagMap = new QPostsHashtagMap("postsHashtagMap");

    public final QHashTag hashTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosts post;

    public QPostsHashtagMap(String variable) {
        this(PostsHashtagMap.class, forVariable(variable), INITS);
    }

    public QPostsHashtagMap(Path<? extends PostsHashtagMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostsHashtagMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostsHashtagMap(PathMetadata metadata, PathInits inits) {
        this(PostsHashtagMap.class, metadata, inits);
    }

    public QPostsHashtagMap(Class<? extends PostsHashtagMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTag = inits.isInitialized("hashTag") ? new QHashTag(forProperty("hashTag")) : null;
        this.post = inits.isInitialized("post") ? new QPosts(forProperty("post"), inits.get("post")) : null;
    }

}

