package com.culticare.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberLikePosts is a Querydsl query type for MemberLikePosts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberLikePosts extends EntityPathBase<MemberLikePosts> {

    private static final long serialVersionUID = 1040779213L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberLikePosts memberLikePosts = new QMemberLikePosts("memberLikePosts");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberId = createString("memberId");

    public final QPosts post;

    public QMemberLikePosts(String variable) {
        this(MemberLikePosts.class, forVariable(variable), INITS);
    }

    public QMemberLikePosts(Path<? extends MemberLikePosts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberLikePosts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberLikePosts(PathMetadata metadata, PathInits inits) {
        this(MemberLikePosts.class, metadata, inits);
    }

    public QMemberLikePosts(Class<? extends MemberLikePosts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPosts(forProperty("post"), inits.get("post")) : null;
    }

}

