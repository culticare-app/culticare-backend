package com.culticare.news.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberNewsScrap is a Querydsl query type for MemberNewsScrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberNewsScrap extends EntityPathBase<MemberNewsScrap> {

    private static final long serialVersionUID = -1342261575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberNewsScrap memberNewsScrap = new QMemberNewsScrap("memberNewsScrap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final QNews news;

    public QMemberNewsScrap(String variable) {
        this(MemberNewsScrap.class, forVariable(variable), INITS);
    }

    public QMemberNewsScrap(Path<? extends MemberNewsScrap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberNewsScrap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberNewsScrap(PathMetadata metadata, PathInits inits) {
        this(MemberNewsScrap.class, metadata, inits);
    }

    public QMemberNewsScrap(Class<? extends MemberNewsScrap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news"), inits.get("news")) : null;
    }

}

