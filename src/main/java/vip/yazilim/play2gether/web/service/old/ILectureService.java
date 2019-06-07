package vip.yazilim.play2gether.web.service.old;

import vip.yazilim.play2gether.web.entity.old.Lecture;
import vip.yazilim.play2gether.web.service.CrudService;

import java.util.List;

/**
 * @author Emre Sen - 24.05.2019
 * @contact maemresen07@gmail.com
 */
public interface ILectureService extends CrudService<Lecture, String> {

    public List<Lecture> getLectureListByCourse(String courseUuid);

    public String getLectureInfo(String lectureUuid) throws Exception;
}
