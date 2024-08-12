package com.culticare.information.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = 1452266414L;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final QInformation _super = new QInformation(this);

    public final StringPath countryOfOrigin = createString("countryOfOrigin");

    public final StringPath endTime = createString("endTime");

    public final StringPath facility = createString("facility");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath language = createString("language");

    public final DateTimePath<java.time.LocalDateTime> postDate = createDateTime("postDate", java.time.LocalDateTime.class);

    public final StringPath recruitNumber = createString("recruitNumber");

    //inherited
    public final NumberPath<Long> scrapCount = _super.scrapCount;

    public final StringPath startTime = createString("startTime");

    public final StringPath title = createString("title");

    //inherited
    public final StringPath typeName = _super.typeName;

    public final StringPath wage = createString("wage");

    public final StringPath weekendWorkStatus = createString("weekendWorkStatus");

    public final StringPath workingArea = createString("workingArea");

    public final StringPath workingDays = createString("workingDays");

    public final StringPath workType = createString("workType");

    public final StringPath workWelfare = createString("workWelfare");

    public QRecruitment(String variable) {
        super(Recruitment.class, forVariable(variable));
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitment(PathMetadata metadata) {
        super(Recruitment.class, metadata);
    }

}

