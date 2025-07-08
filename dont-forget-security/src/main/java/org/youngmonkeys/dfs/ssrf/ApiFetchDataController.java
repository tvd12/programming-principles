package org.youngmonkeys.dfs.ssrf;

import com.tvd12.ezyhttp.client.HttpClient;
import com.tvd12.ezyhttp.client.request.GetRequest;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Controller("/api/v1")
public class ApiFetchDataController {

    @DoGet("/data")
    public Object dataGet(
        @RequestParam String url
    ) throws Exception {
        HttpClient httpClient = HttpClient.builder()
            .build();
        return httpClient.call(
            new GetRequest()
                .setURL(url)
        );
    }

    public enum ShapeType {
        SQUARE,
        RECTANGLE
    }

    @AllArgsConstructor
    public static class Shape {
        public ShapeType type;
        public int side;
        public int width;
        public int height;
    }

    Map<ShapeType, Function<Shape, Integer>> functionByShapeType =
        new HashMap<>();
    {
        functionByShapeType.put(
            ShapeType.SQUARE,
            (shape) -> shape.side * shape.side
        );
        functionByShapeType.put(
            ShapeType.RECTANGLE,
            (shape) -> shape.width * shape.height
        );
    }

    public int calculate_area(Shape shape) {
        Function<Shape, Integer> func = functionByShapeType.get(shape.type);
        return func.apply(shape);
    }
}
