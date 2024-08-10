package com.culticare.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosts is a Querydsl query type for Posts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosts extends EntityPathBase<Posts> {

    private static final long serialVersionUID = -1371500450L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosts posts = new QPosts("posts");

    public final com.culticare.QBaseTimeEntity _super = new com.culticare.QBaseTimeEntity(this);

    public final QCategories category;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final StringPath loginMemberId = createString("loginMemberId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<PostsHashtagMap, QPostsHashtagMap> postsHashtagMaps = this.<PostsHashtagMap, QPostsHashtagMap>createList("postsHashtagMaps", PostsHashtagMap.class, QPostsHashtagMap.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public final StringPath writerName = createString("writerName");

    public QPosts(String variable) {
        this(Posts.class, forVariable(variable), INITS);
    }

    public QPosts(Path<? extends Posts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosts(PathMetadata metadata, PathInits inits) {
        this(Posts.class, metadata, inits);
    }

    public QPosts(Class<? extends Posts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategories(forProperty("category")) : null;
    }

}

