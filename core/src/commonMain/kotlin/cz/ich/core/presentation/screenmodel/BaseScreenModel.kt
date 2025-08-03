package cz.ich.core.presentation.screenmodel

import cafe.adriel.voyager.core.model.ScreenModel
import com.diamondedge.logging.logging
import cz.ich.core.presentation.model.UiEvent
import cz.ich.core.presentation.model.UiState
import kotlinx.coroutines.flow.StateFlow

/**
 * Abstract class for all ScreenModels.
 */
abstract class BaseScreenModel<T, E : UiEvent> : ScreenModel {

    protected val log = logging(this::class.simpleName)

    /**
     * State of View that is observed in compose screens. It uses [StateFlow] to limit accessibility
     * of modifying it from outside of the ViewModel.
     */
    abstract val viewState: StateFlow<UiState<T>>

    /**
     * To handle user actions from UI in the ViewModel. Each action has to be defined in
     * sealed class that implements [UiEvent].
     *
     * @param event specific event that is part [E] class.
     */
    abstract fun onEvent(event: E)
}