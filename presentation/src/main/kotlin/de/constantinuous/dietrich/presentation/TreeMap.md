procedure squarify(list of real children, list of real row, real w) begin
    real
    c = head(children);
    if worst(row, w) <= worst(row++[c], w) then
        squarify(tail(children), row++[c], w)
    else
        layoutrow(row);
        squarify(children, [], width());
    fi
end