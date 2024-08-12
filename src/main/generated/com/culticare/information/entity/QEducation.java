package com.culticare.information.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEducation is a Querydsl query type for Education
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEducation extends EntityPathBase<Education> {

    private static final long serialVersionUID = -1438660038L;

    public static final QEducation education = new QEducation("education");

    public final QInformation _super = new QInformation(this);

    public final DateTimePath<java.time.LocalDateTime> applyEndDate = createDateTime("applyEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> applyStartDate = createDateTime("applyStartDate", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> eduEndDate = createDateTime("eduEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> eduStartDate = createDateTime("eduStartDate", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath language = createString("language");

    public final NumberPath<Integer> personnel = createNumber("personnel", Integer.class);

    public final StringPath qualification = createString("qualification");

    //inherited
    public final NumberPath<Long> scrapCount = _super.scrapCount;

    public final StringPath title = createString("title");

    //inherited
    public final StringPath typeName = _super.typeName;

    public QEducation(String variable) {
        super(Education.class, forVariable(variable));
    }

    public QEducation(Path<? extends Education> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEducation(PathMetadata metadata) {
        super(Education.class, metadata);
    }

}

