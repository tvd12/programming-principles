package org.youngmonkeys.yagni.v1.controller;

import java.util.List;

import org.youngmonkeys.yagni.v1.dto.BaseDTO;

public abstract class BaseController<I, D extends BaseDTO> {

    public abstract List<D> getDataList(int skip, int limit);

    public abstract D getDataById(I id);

    public abstract I addData(D data);

    public abstract void deleteDataById(I id);

    public abstract void updateData(D data);
}
