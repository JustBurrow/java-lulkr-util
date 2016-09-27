/**
 * @see https://github.com/JustBurrow/common
 */
package kr.lul.test;

import static kr.lul.util.Asserts.le;
import static kr.lul.util.Asserts.notNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import kr.lul.data.Updatable;

/**
 * {@link Updatable}용 테스트 도구.
 *
 * @author Just Burrow
 * @since 2016. 9. 27.
 */
public abstract class AssertUpdatable {
  /**
   * 지정한 시각 이후에 생성됐음을 테스트한다.
   *
   * @param updatable
   * @param create
   *          생성 시각 최대값(포함).
   * @throws AssertionError
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static void createdAfter(Updatable updatable, Instant create) throws AssertionError {
    notNull(updatable, "updatable");
    notNull(create, "create");

    assertThat(updatable.getCreate()).isGreaterThanOrEqualTo(create);
    assertThat(updatable.getUpdate()).isGreaterThanOrEqualTo(updatable.getCreate());
  }

  /**
   * @param updatable
   * @param update
   *          갱신 시각 최대값(포함).
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static void updatedAfter(Updatable updatable, Instant update) {
    notNull(updatable, "updatable");
    notNull(update, "update");

    assertThat(updatable.getUpdate()).isNotNull().isGreaterThanOrEqualTo(update);
    assertThat(updatable.getCreate()).isNotNull().isLessThanOrEqualTo(updatable.getUpdate());
  }

  /**
   * @param updatable
   * @param create
   *          생성 시각 최대값(포함).
   * @param update
   *          갱신 시각 최대값(포함).
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static void createdAfterAndUpdatedAfter(Updatable updatable, Instant create, Instant update) {
    notNull(updatable, "updatable");
    notNull(create, "create");
    notNull(update, "update");

    le(create, update);
    assertThat(updatable.getCreate()).isNotNull().isGreaterThanOrEqualTo(create);
    assertThat(updatable.getUpdate()).isNotNull().isGreaterThanOrEqualTo(update);
  }

  protected AssertUpdatable() {
    throw new UnsupportedOperationException();
  }
}
