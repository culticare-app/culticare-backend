package com.culticare.comments.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComments is a Querydsl query type for Comments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComments extends EntityPathBase<Comments> {

    private static final long serialVersionUID = -1001969728L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComments comments = new QComments("comments");

    public final com.culticare.QBaseTimeEntity _super = new com.culticare.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final com.culticare.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.culticare.posts.entity.QPosts post;

    public QComments(String variable) {
        this(Comments.class, forVariable(variable), INITS);
    }

    public QComments(Path<? extends Comments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComments(PathMetadata metadata, PathInits inits) {
        this(Comments.class, metadata, inits);
    }

    public QComments(Class<? extends Comments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.culticare.member.entity.QMember(forProperty("member")) : null;
        this.post = inits.isInitialized("post") ? new com.culticare.posts.entity.QPosts(forProperty("post"), inits.get("post")) : null;
    }

}

