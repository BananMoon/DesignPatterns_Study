package moonz.study.designpatterns.creation.good;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum ShipColorType {
    WHITE("white"),
    BLACK("black");

    private final String name;
}
