package com.culticare.information.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWelfareCenter is a Querydsl query type for WelfareCenter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWelfareCenter extends EntityPathBase<WelfareCenter> {

    private static final long serialVersionUID = 326806931L;

    public static final QWelfareCenter welfareCenter = new QWelfareCenter("welfareCenter");

    public final QInformation _super = new QInformation(this);

    public final StringPath address = createString("address");

    public final StringPath facilityType = createString("facilityType");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath manager = createString("manager");

    public final StringPath name = createString("name");

    public final StringPath regionName = createString("regionName");

    //inherited
    public final NumberPath<Long> scrapCount = _super.scrapCount;

    public final StringPath telephone = createString("telephone");

    public final StringPath typeDetail = createString("typeDetail");

    //inherited
    public final StringPath typeName = _super.typeName;

    public final StringPath zipCode = createString("zipCode");

    public QWelfareCenter(String variable) {
        super(WelfareCenter.class, forVariable(variable));
    }

    public QWelfareCenter(Path<? extends WelfareCenter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWelfareCenter(PathMetadata metadata) {
        super(WelfareCenter.class, metadata);
    }

}

