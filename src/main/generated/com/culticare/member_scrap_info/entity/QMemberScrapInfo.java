package com.culticare.member_scrap_info.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberScrapInfo is a Querydsl query type for MemberScrapInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberScrapInfo extends EntityPathBase<MemberScrapInfo> {

    private static final long serialVersionUID = 2111472098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberScrapInfo memberScrapInfo = new QMemberScrapInfo("memberScrapInfo");

    public final com.culticare.QBaseTimeEntity _super = new com.culticare.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.culticare.information.entity.QInformation information;

    public final com.culticare.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath typeName = createString("typeName");

    public QMemberScrapInfo(String variable) {
        this(MemberScrapInfo.class, forVariable(variable), INITS);
    }

    public QMemberScrapInfo(Path<? extends MemberScrapInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberScrapInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberScrapInfo(PathMetadata metadata, PathInits inits) {
        this(MemberScrapInfo.class, metadata, inits);
    }

    public QMemberScrapInfo(Class<? extends MemberScrapInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.information = inits.isInitialized("information") ? new com.culticare.information.entity.QInformation(forProperty("information")) : null;
        this.member = inits.isInitialized("member") ? new com.culticare.member.entity.QMember(forProperty("member")) : null;
    }

}

