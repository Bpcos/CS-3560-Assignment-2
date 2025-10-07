package Controller;

public record InputState(
        boolean isLeft,
        boolean isRight,
        boolean isUp,
        boolean isDown,
        boolean isJump,
        boolean isFire
) {}
