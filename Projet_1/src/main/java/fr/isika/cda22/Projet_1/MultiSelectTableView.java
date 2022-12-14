package fr.isika.cda22.Projet_1;

import java.util.HashSet;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class MultiSelectTableView<S> extends TableView<Stagiaire4Liste> {


	private boolean _update = false;

    private HashSet<Integer> _selectedIndices = new HashSet<>();
    private int _lastSelectedIndex = -1;
    private int _previousSelectedIndex = -1;

    /**
     * @see TableView#TableView()
     */
    public MultiSelectTableView() {
        super();
        _init();
    }

    /**
     * @see TableView#TableView(ObservableList)
     */
    public MultiSelectTableView(ObservableList<Stagiaire4Liste> items) {
        super(items);
        _init();
    }

    private void _init() {
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (_update || newValue == null)
                return ;

            updateSelection(sm -> {
                Integer newIndex = newValue.intValue();
                _previousSelectedIndex = _lastSelectedIndex;
                _lastSelectedIndex = newIndex;

                _toggle(newIndex);

                Platform.runLater(this::_makeSelection);
            });
        });

        setOnMouseClicked(event -> {
            if (event.isShiftDown() && _previousSelectedIndex != -1) {
                updateSelection(sm -> {
                    for (int i = _previousSelectedIndex + 1; i < _lastSelectedIndex ; ++i)
                        _toggle(i);
                    _makeSelection();
                });
            }
            if (event.isControlDown()) {
                _selectedIndices.clear();
                _selectedIndices.addAll(getSelectionModel().getSelectedIndices());
            }
        });

    }

    private void _toggle(Integer index) {
        if (!_selectedIndices.remove(index))
            _selectedIndices.add(index);
    }

    private void _makeSelection() {
        getSelectionModel().clearSelection();
        _selectedIndices.forEach(index -> getSelectionModel().select(index));
    }

    /**
     * You should always use this method when planning to update the selection.
     *
     * For example: <code>tableView.updateSelection(sm -> sm.clearAll());</code>
     *
     * @param c A function that will mutate the selection.
     */
    public void updateSelection(Consumer<TableViewSelectionModel<Stagiaire4Liste>> c) {
        _update = true;
        TableViewSelectionModel<Stagiaire4Liste> sm = getSelectionModel();
        c.accept(sm);
        Platform.runLater(() -> {
            sm.select(null);
            _selectedIndices.clear();
            _selectedIndices.addAll(sm.getSelectedIndices());
            _update = false;
        });
    }




}
